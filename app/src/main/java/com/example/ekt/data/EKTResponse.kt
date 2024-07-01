package com.example.ekt.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class EKTResponse(
    val mensaje: String,
    val advertencia: String,
    val resultado: Result,

    )

data class Result(
    val paginacion: Pagination,
    val categoria: String,
    val productos: List<Product>,
)

data class Pagination(
    val pagina: Long,
    val totalPaginas: Long,
    val totalRegistros: Long,
    val totalRegistrosPorPagina: Long,
)

@Parcelize
data class Product(
    val id: String,
    val idLinea: Int,
    val codigoCategoria: String,
    val idModalidad: Int,
    val relevancia: Int,
    val lineaCredito: String,
    val pagoSemanalPrincipal: Double,
    val plazoPrincipal: Int,
    val disponibleCredito: Boolean,
    val abonosSemanales: List<WeeklyPayments>,
    val sku: String,
    val nombre: String,
    val urlImagenes: List<String>,
    val precioRegular: Double,
    val precioFinal: Double,
    val porcentajeDescuento: Double,
    val descuento: Boolean,
    val precioCredito: Double,
    val montoDescuento: Double,
) : Parcelable

@Parcelize
data class WeeklyPayments(
    val plazo: Int,
    val montoAbono: Double,
    val montoDescuentoAbono: Double,
    val montoUltimoAbono: Double,
    val montoFinalCredito: Double,
    val idPromocion: Int,
    val montoDescuentoElektra: Double,
    val montoDescuentoBanco: Double,
    val precio: Double,
    val montoAbonoDigital: Double,
) : Parcelable