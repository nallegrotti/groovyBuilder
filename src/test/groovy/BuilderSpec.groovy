import MockBuilder

class Simple {
	String aString
	Integer anInt
	Double aDouble
	Boolean aBoolean
	BigDecimal aDecimalNumber
	Collection<String> someCollection
	EnumType anEnum
	EnumComplexType aComplexEnum
}

enum EnumType {
    FIRST_VALUE, SECOND_VALUE, LAST_VALUE
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

class BuilderSpec  extends spock.lang.Specification {

	def "can build an simple class"() {
		given: "A simple class with primitive attributes"
		when: "an object is builded"
			def b
			use(MockBuilder){
				b = Simple.build()
			}
		then: "The object gets default values of every attribute"
			b.aString != null
			b.anInt != null 
			b.aDouble != null
			b.aBoolean != null
			b.aDecimalNumber != null
			b.someCollection == []
			b.anEnum != null
			b.aComplexEnum != null
	}

	def "can build a simple class with prototype values"() {
		given: "A simple class with primitive attributes"
		when: "an object is builded with some prototype values"
			def b
			use(MockBuilder){
				b = Simple.build(aString: 'example', aDecimalNumber: 1.5)
			}
		then: "those values are used instead of default ones"
			b.aString == 'example'
			b.aDecimalNumber == 1.5
	}

}