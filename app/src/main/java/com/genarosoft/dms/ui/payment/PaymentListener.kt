package com.genarosoft.dms.ui.payment

interface PaymentListener {
    fun openPaymentMethodDialog(paymentMethod: String?)
//    fun onAddedPaymentMethod(paymentMethod: String)
    fun onDeletePaymentMethod(position: Int)
}