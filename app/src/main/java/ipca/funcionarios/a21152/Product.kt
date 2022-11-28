package ipca.funcionarios.a21152

class Product {
    var brand : String? = null
    var model : String? = null
    var qt : Int? = null
    var price : Double? = null

    constructor(brand: String?, model: String?, qt: Int?, price: Double?) {
        this.brand = brand
        this.model = model
        this.qt = qt
        this.price = price
    }
}