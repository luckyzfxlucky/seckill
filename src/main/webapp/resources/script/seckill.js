//存放主要交互逻辑js代码
//javascript模块化    java用package来模块化，js中没有，有json的表示方式
var seckill = {
    //封装秒杀相关ajax的url,方便维护和修改
    URL: {
        now : function () {
            return '/seckill/time/now';
        },
        exposer : function (seckillId) {
            return '/seckill/'+seckillId+'/exposer';
        },
        execution : function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execution';
        }
    },
    handleSeckillkill : function (seckillId,node) {
        //处理秒杀逻辑:获取秒杀地址，控制显示逻辑，执行秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId),{},function (result) {
            //在回调函数中，执行交互流程
            if(result && result['success']){
                var exposer = result['data'];
                if(exposer['exposed']){
                    //开启秒杀
                    //获取秒杀地址
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId,md5);
                    console.log("killUrl:"+killUrl);
                    //用one，不用click绑定：因为click一直在绑定，one只绑定一次点击事件
                    $('#killBtn').one('click',function () {
                        //执行秒杀请求
                        //1：先禁用按钮
                        $(this).addClass('disabled');
                        //2:发送秒杀请求，执行秒杀
                        $.post(killUrl,{},function (result) {
                            if(result && result['success']){
                                var killResult=result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //3：显示秒杀结果
                                node.html('<span class="label label-success">'+stateInfo+'</span>')
                            }
                        });
                    });
                    node.show();
                }else {
                    //未开启秒杀，可能各个浏览器，或客户端有计时偏差
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    //重新计算计时逻辑
                    seckill.countdown(seckillId,now,start,end);
                }
            }else {
                console.log("result"+result);
            }
        });
    },
    //验证手机号
    ValidatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {//不为空，长度为11，是数字（isNaN表示非数字）
            return true;
        } else {
            return false;
        }
    },
    countdown : function (seckillId,nowTime,startTime,endTime) {
        var seckillBox = $('#seckill-box');
        //时间的判断
        if(nowTime > endTime){
            //秒杀结束！
            seckillBox.html('秒杀结束！');
        }else if(nowTime < startTime){
            //秒杀未开始！计时事件绑定
            var killTime = new Date(startTime + 1000);//+1s防止用户端的一个计时服务之后，出现计时时间的偏移，不加也可以
            seckillBox.countdown(killTime,function (event) {
               //时间格式
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //时间完成后回调事件
            }).on('finish.countdown',function () {
                //获取秒杀地址，控制显示逻辑，执行秒杀
                seckill.handleSeckillkill(seckillId,seckillBox);
            })
        }else {
            //秒杀开始
            seckill.handleSeckillkill(seckillId,seckillBox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录，计时交互
            //规划我们的交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');//js访问jsp中的json
            //验证手机号
            if (!seckill.ValidatePhone(killPhone)) {
                //未登录，绑定手机号
                //控制输出
                var killPhoneModal = $('#killPhoneModel');
                //显示弹出层
                killPhoneModal.modal({
                    //显示弹出层，如果未填写正确的手机号，弹出层是不能关闭的，所以要把一些默认的属性给处理一下
                    show: true,
                    backdrop: 'static',  //禁止位置关闭（防止点击别的位置，弹出层关掉）
                    keyboard: false    //关闭键盘事件（防止点击键盘上的ESC关闭）
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log("inputPhone" + inputPhone);//TODO
                    if (seckill.ValidatePhone(inputPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
                ;
            }
            //已经登录
            //计时交互
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(),{},function (result) {
               if(result && result['success']){
                   var nowTime = result['data'];
                   //时间判断,计时交互
                   //console.log("nowTime1"+nowTime);
                   //console.log("seckillId"+seckillId);
                   seckill.countdown(seckillId,nowTime,startTime,endTime);
               }else {
                   console.log('result'+result);
               }
            });
        }
    }
}