package ipca.funcionarios.a21152

class Product {
    var brand : String? = null
    var model : String? = null
    var qt : Int? = null
    var price : Double? = null
    var promotion : Boolean? = false

    constructor(brand: String?, model: String?, qt: Int?, price: Double?, promotion: Boolean?) {
        this.brand = brand
        this.model = model
        this.qt = qt
        this.price = price
        this.promotion = promotion
    }
}