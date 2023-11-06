package net.azarquiel.nbateams.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//Result guarda todos los datos del json
//@SerializedName para poder cambiarle de nombre
//Creamos un dataclass para cada uno de los objetos que aparezcan[], list{}
//las etiquetas no se pueden cambiar, lo que va entre parentesis

    data class Team (
        var id:String,
        var name:String,
        var conference:String,
        var record:String,
        var teamLogoUrl:String):Serializable
    data class Player (
        var id:String,
        var firstName:String,
        var lastName:String,
        var team:String,
        var position:String,
        var dateOfBirth:String,
        var height:String,
        var weight:String,
        var jerseyNumber:String,
        var age:String,
        var headShotUrl:String
    )
