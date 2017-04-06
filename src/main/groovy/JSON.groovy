class JSON {
	static getJson(o){
		new groovy.json.JsonBuilder(o)
	}
}