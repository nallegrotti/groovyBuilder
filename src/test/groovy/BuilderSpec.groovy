import MockBuilder

class Simple {
	String aString
	Integer anInt
	Double aDouble
	BigDecimal aDecimalNumber
	Collection<String> someCollection
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
			b.aDecimalNumber != null
			b.someCollection == []
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