import MockBuilder
import JSON

class Persona{
    String nombre
    String apellido 
    Document document
}

class Libro {
    String titulo 
    Persona autor
    BigDecimal precio
}

class Bibloteca {
	String nombre
	String direccion
	Collection<Libro> libros
}

enum DocumentType {
    DNI, LC, LE, CI
}

enum EnumComplexType {
    FIRST_VALUE("first", 1),
    SECOND_VALUE("second", 2),
    LAST_VALUE('last', 1000)

    String name
    Integer value

    EnumComplexType(name, value){
        this.name = name
        this.value = value
    }
}

class Document {
    Integer number
    DocumentType type
}

use(MockBuilder, JSON){
    def unLibro = Libro.build(
		titulo: 'Mi libro', 
		precio: 15.50, 
		autor: Persona.build(nombre: 'anonimo')
	)
    println unLibro.json

    def biblioteca = Bibloteca.build(nombre: 'publica')
    biblioteca.libros << unLibro

    println biblioteca.json

    println Libro.build().json

    println Document.build().json

    println EnumComplexType.values()[0].dump()
}