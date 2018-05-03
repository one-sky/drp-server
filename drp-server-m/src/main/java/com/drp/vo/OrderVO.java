package com.drp.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVO {

    private Integer id;

    //订单号
    private String orderCode;

    //分销商Id
    private Integer distributorId;

    //下单人账号id
    private Long accountId;

    // 1、一件代发；2、批发
    private Integer type;

    //总金额
    private BigDecimal totalAmount;

    //支付金额
    private BigDecimal paidAmount;

    private List<OrderItemVO> orderItemVOList;

    //订单状态
    private Integer status;

    private String statusDesc;

    //订单总运费
    private BigDecimal shippingCost;

    //交易流水号
    private String trancationId;

    //支付状态 0-未付款 2-已支付
    private Byte paymentStatus;

    private String paymentChannel;//支付渠道

    //支付时间
    private Date paymentTime;

    //订单商品数量
    private Integer orderItemQuantity;

    //商品总计(未含运费)
    private BigDecimal itemSubtoal;

    //下单时间
    private Date orderTime;

    //取消类型（买家取消，厂家取消，系统取消）
    private Byte cancelType;

    private String cancelReason;

    private Date cancelTime;

    private String salesRuleName;

    private BigDecimal salesRuleDiscountAmount;

    private BigDecimal shippingCostDiscountAmount;

    private String buyerMessage;

    private Date deliveryTime;

    private Date finishTime;

    private Integer createBy;

    private Date createTime;

    private Integer lastUpdateBy;

    private Date lastUpdateTime;

    private String receiveName;

    private String receivePhone;

    private String province;

    private String provinceDesc;

    private String city;

    private String cityDesc;

    private String area;

    private String areaDesc;

    private String detailAddress;

    private String deliveryName;

    private String deliveryCode;

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getTrancationId() {
        return trancationId;
    }

    public void setTrancationId(String trancationId) {
        this.trancationId = trancationId;
    }

    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(Integer orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    public BigDecimal getItemSubtoal() {
        return itemSubtoal;
    }

    public void setItemSubtoal(BigDecimal itemSubtoal) {
        this.itemSubtoal = itemSubtoal;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Byte getCancelType() {
        return cancelType;
    }

    public void setCancelType(Byte cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getSalesRuleName() {
        return salesRuleName;
    }

    public void setSalesRuleName(String salesRuleName) {
        this.salesRuleName = salesRuleName;
    }

    public BigDecimal getSalesRuleDiscountAmount() {
        return salesRuleDiscountAmount;
    }

    public void setSalesRuleDiscountAmount(BigDecimal salesRuleDiscountAmount) {
        this.salesRuleDiscountAmount = salesRuleDiscountAmount;
    }

    public BigDecimal getShippingCostDiscountAmount() {
        return shippingCostDiscountAmount;
    }

    public void setShippingCostDiscountAmount(BigDecimal shippingCostDiscountAmount) {
        this.shippingCostDiscountAmount = shippingCostDiscountAmount;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public List<OrderItemVO> getOrderItemVOList() {
        return orderItemVOList;
    }

    public void setOrderItemVOList(List<OrderItemVO> orderItemVOList) {
        this.orderItemVOList = orderItemVOList;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Integer lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}