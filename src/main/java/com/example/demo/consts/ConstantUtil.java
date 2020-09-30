package com.example.demo.consts;

public final class ConstantUtil {


    /**
     * 分页常量strat
     */
    public final static int PAGE_SIZE = 12;
    public final static int RECOMMEND_PAGE_SIZE = 3;
    //APIpageSize
    public final static int API_PAGE_SIZE = 20;
    /** 分页常量end */


    /**
     * 缓存strat
     */
    // 验证码1分钟
    public final static Long TIME_VERIFYCODE = 60L;
    // redis验证码缓存一分钟
    public final static int TIME_REDISCODE = 60;

    // 签名Key过期时间1小时
    public final static Long TIME_SIGN_KEY = 3600000L;
    // 签名Key过期时间2小时
    public final static Long TIME_SIGN_KEY_DEVICE = 7200000L;
    // 签名key过期时间5分钟
    public final static Long TIME_SIGN_KET_FIVE_MINUTE = 300000L;

    /** 缓存常量end */

    /**
     * redis分布式锁相关常量
     */
    // jedis set成功
    public static final String LOCK_SUCCESS = "OK";
    // jedis release成功
    public static final Long RELEASE_SUCCESS = 1L;
    // jedis 唯一性判定
    public static final String SET_IF_NOT_EXIST = "NX";
    // jedis 设置过期时间(单位：秒)
    public static final String SET_WITH_EXPIRE_TIME_EX = "EX";
    // jedis 设置过期时间(单位：毫秒)
    public static final String SET_WITH_EXPIRE_TIME_PX = "PX";
    // 秒杀分布式锁key值
    public static final String SPIKE_QUEUE_KEY= "spikeQueueKey";
    // 退款分布式锁key值
    public static final String REFUND_QUEUE_KEY= "refundQueueKey";

    /** redis分布式锁常量end */


    /**
     * 文字常量
     */
    public static final String ADMIN_NAME = "管理员";

    /**
     * // 平台店铺
     */
    public static final String PLANTFORM_SHOP_ID = "1";

    /**
     * // 新人店铺
     */
    public static final String NEWER_SHOP_ID = "2";

    /**
     * // 待评论
     */
    public static final String ORDER_EVALUATE_STATE_WAIT_COMMENT = "001";

    /**
     * // 全部评论
     */
    public static final String ORDER_EVALUATE_STATE_ALREADY_COMMENT = "003";

    /**
     * // 部分评论
     */
    public static final String ORDER_EVALUATE_STATE_SOME_COMMENT = "002";

    /**
     * // 商品单价计算类型 斤
     */
    public static final String GOODS_TYPE_GRAMS = "001";

    /**
     * // 商品单价计算类型 数量
     */
    public static final String GOODS_TYPE_NUMBER = "002";

    /**
     * // 普通商品
     */
    public static final String GOODS_GOODS_SHOP = "GoodsShop";

    /**
     * // 活动商品平台
     */
    public static final String GOODS_POINT_STORE = "PointStore";

    /**
     * // 活动商品一般
     */
    public static final String GOODS_OTHER_ACTIVITY = "OtherActivity";

    /**
     * // 活动商品预售
     */
    public static final String GOODS_ADVANCE_SALE_ACTIVITY = "AdvanceSaleActivity";

    /**
     * // 活动商品秒杀
     */
    public static final String GOODS_SPIKE_ACTIVITY = "SpikeActivity";

    /**
     * // 活动商品团购
     */
    public static final String GOODS_GROUP_ACTIVITY = "GroupActivity";

    /**
     * // 活动商品新人专享
     */
    public static final String GOODS_NEWER_STORE = "NewerStore";

    /**
     * // 包装袋分类
     */
    public static final String PACK_CATEGORY = "330";

    /**
     *  退款消息队列
     */
    public static final String REFUND_QUEUE_NAME = "bRefundQueue";
    public static final String ROUTINGKEY_REFUND = "refund";

    /**
     *  退款消息队列最大长度
     */
    public static final int REFUND_MAX_NUM = 1000000;


    /**
     * 秒杀广播队列最大长度
     */
    public static final int FANOUT_MAX_NUM = 100;
    /**
     * 短信相关常量
     */
    //短信模板
    public static final String REGISTID = "SMS_152375396";

    /**
     * 快速生成controller和service相关工具
     */
    public static final String BASE_PACKAGE = "com.example.demo";//生成代码所在的基础包名称，可根据自己公司的项目修改（注意：这个配置修改之后需要手工修改src目录项目默认的包路径，使其保持一致，不然会找不到类）

    public static final String BASE_PACKAGE_FRAME = "com.example.demo";//框架所在的基础包

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".entity";//生成的Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//生成的Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//生成的Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//生成的ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";//生成的Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE_FRAME + ".utils.code.Mapper";//Mapper插件基础接口的完全限定名

    /**
     * 配送方式
     */
    public enum disPatchWays {

        // 自提
        pickUp("001"),
        // 送货上门
        disPatch("002");
        private String val;

        disPatchWays(String val) {
            this.val = val;
        }

        public String getCode() {
            return val;
        }
    }

    /**
     * 优惠券状态
     */
    public enum CouponStatus {

        // 已领取
        alreadyReceived("001"),
        // 已使用
        alreadyUsed("002"),
        // 退款已返还
        alreadyReturned("003");

        private String val;

        CouponStatus(String val) {
            this.val = val;
        }

        public String getCode() {
            return val;
        }
    }

    /**
     * 订单状态
     */
    public enum OrderStatus {

        // 待付款
        waitPayment("001"),
        // 待发货
        waitDelivery("002"),
        // 配送中
        delivering("003"),
        // 已完成
        alreadyCompleted("004"),
        // 已取消
        alreadyCancled("005"),
        // 退款申请中
        refundApplying("006"),
        // 待取货
        picking("007");

        private String val;

        OrderStatus(String val) {
            this.val = val;
        }

        public String getCode() {
            return val;
        }
    }


    /**
     * 退款状态
     */
    public enum RefundStatus {

        // 无退款
        noRefund("001"),
        // 退款中
        refunding("002"),
        // 已退款
        refunded("003"),
        // 退款申请中
        refundApplying("004"),
        // 已拒绝
        refundRefused("005");

        private String val;

        RefundStatus(String val) {
            this.val = val;
        }

        public String getCode() {
            return val;
        }
    }


    /**
     * fanout消息种类
     */
    public enum MessageType {
        // 启动秒杀队列监听
        startSpike("001"),
        // 关闭秒杀队列
        stopSpike("002");

        private String type;

        MessageType(String type) {
            this.type = type;
        }


        public String getType() {
            return type;
        }
    }
    /**
     * 判断分类排序的类别
     */
    public enum SortType {
        //默认
        AssortDefault("001"),
        //销量
        AssortSalesVolume("002"),
        //好评
        AssortPraise("003"),
        //价格降序
        AssortPriceDown("004"),
        //价格升序
        AssortPriceUp("005");



        private String type;

        SortType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

}
