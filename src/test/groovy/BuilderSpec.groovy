import MockBuilder

class Simple {
	String aString
	Integer anInt
	Long aLong
	long aPrimitiveLong
	Double aDouble
	double aPrimitiveDouble
	Boolean aBoolean
	boolean aPrimitiveBoolean
	BigDecimal aDecimalNumber
	Collection<String> someCollection
	EnumType anEnum
	EnumComplexType aComplexEnum
	Map<String, Integer> aMap
	Date aDate

	final Long aFinalLong = 10L;
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
			b.aLong != null
			b.aPrimitiveLong == 0
			b.aDouble != null
			b.aBoolean != null
			b.aDecimalNumber != null
			b.someCollection == []
			b.anEnum != null
			b.aComplexEnum != null
			b.aFinalLong == 10L
			b.aMap != null
			b.aDate != null
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