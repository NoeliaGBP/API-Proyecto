Clases(pojo) UpperCamelCase AreaIngrediente
atributos\ metodos LowerCamelCase insertarIngrediente()
ConnectionDB //Personal
DAO() AreaIngredienteDao
Servicio ServiceAreaIngrediente

//Bean
Persona(int id, String nombre){
}

//Pojo
//Si solo implemetas el constructor vacío es un POJO
Persona(){
}

## Nombramiento de los documentos Bean y Dao:

Ingrediente
AreaIngrediente
Platillo
UnidadMedida
Preparacion
TipoPlatillo
TipoMenu
Sucursal
Pedido
Persona
Usuario
Promocion
Direccion
Rol
Contacto
Menu
MenuDia
Ponderacion
IngredientePlatillo
PlatilloMenu
ImagenPlatillo
PedidoTienePlatillo
PedidoTienePromocion
SucursalTieneUsuario
Dia
TipoDia
TipoContacto
PromocionTienePlatilloMenu
Precio

## En orden

[D]1.-AreaIngrediente
    [x]Pojo
    [x]Service
    [x]DAO
[D]2.-Ingrediente
    [x]Pojo
    [x]Service
    [x]DAO
[D]3.-UnidadMedida --Solo cuenta con el getbyid en el dao
    [x]Pojo
    [/]Service
    [/]DAO
[D]4.-TipoPlatillo 
    [x]Pojo
    []Service
    []DAO
[D]5.-Platillo
    [x]Pojo
    [x]Service
    [x]DAO
[D]6.-IngredientePlatillo
    [x]Pojo
    []Service
    []DAO
[S]7.-Preparacion
    [x]Pojo
    []Service
    []DAO
[S]8.-Precio
    [x]Pojo
    []Service
    []DAO
[S]9.-ImagenPlatillo
    [x]Pojo
    []Service
    []DAO
[S]10.-Promocion
    [x]Pojo
    []Service
    []DAO
[S]11.-PromocionTienePlatilloMenu
    [x]Pojo
    []Service
    []DAO
[S]12.-Sucursal
    [x]Pojo
    [x]Service
    [x]DAO
[E]13.-TipoMenu
    [x]Pojo
    []Service
    [x]DAO
[E]14.-Menu
    [x]Pojo
    []Service
    [x]DAO
    [x]Pojo
    []Service
    [x]DAO
[E]16.-PlatilloMenu
    [x]Pojo
    []Service
    [x]DAO
[E]17.-Dia
    [x]Pojo
    []Service
    [x]DAO
[E]18.-TipoDia
    [x]Pojo
    []Service
    [x]DAO
//Inicio de actividades Noelia
[N]19.-Persona
    [x]Pojo
    [x]Service
    [x]DAO
[N]20.-TipoContacto
    [x]Pojo
    [x]Service
    [x]DAO
[N]21.-Contacto //Falta servicio para obtener los contactos por especifico según cada usuario
    [x]Pojo
    [x]Service
    [x]DAO
[N]22.-Rol
    [x]Pojo
    [x]Service
    [x]DAO
[N]23.-Usuario //Queda pendiente revisar lo correspondiente a la actualización "contraseña"
//Queda pendiente el regristro de una cuenta "según el rol" y login 
    [x]Pojo
    []Service
    [x]DAO
//Fin actividades Noelia
//Inicio actividades Manuel
[M]24.-SucursalTieneUsuario
    [x]Pojo
    [x]Service
    [X]DAO
[M]25.-Direccion
    [x]Pojo
    [X]Service
    [X]DAO
[M]26.-Pedido
    [x]Pojo
    [x]Service
    [x]DAO
[M]27.-PedidoTienePlatillo
    [x]Pojo
    []Service
    []DAO
[M]28.-PedidoTienePromocion 
    [x]Pojo
    []Service
    []DAO
[M]29.-Ponderacion
    [x]Pojo
    [x]Service
    [x]DAO
