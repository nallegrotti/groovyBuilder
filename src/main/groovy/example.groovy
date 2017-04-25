import MockBuilder
import JSON
import java.lang.reflect.Modifier;

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

class Primitivos {
    int i 
    long l 
    boolean b 
    double d
}

class Finals {
    final static int i_sf = 1;
    final int i_f = 2;
    int i = 3;
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

    Primitivos.declaredFields.each{ f -> 
        println "$f.type -> ${f.type.isCase(0)} || ${f.type in [int]}"
    }
    
    Finals.declaredFields.each{ f -> 
        println "${f.modifiers & Modifier.FINAL}"
    }
}