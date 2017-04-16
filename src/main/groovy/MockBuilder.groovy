class MockBuilder{

    public static Object build(Class c, model){
        def r = c.newInstance()
        def doNotTouch = ['metaClass', '$staticClassInfo', '__$stMC', '$callSiteArray']

        model.each{ k, v -> 
            r[k] = v
            doNotTouch << k
        }

        c.declaredFields.each{
            if (!(it.name in doNotTouch)){
                if (it.type == String){
                    r[it.name] = it.name
                }else if (it.type.isCase(0)){
                    r[it.name] = 0
                }else if (it.type.isCase(0D)){
                    r[it.name] = 0D
                }else if (it.type == BigDecimal){
                    r[it.name] = BigDecimal.ZERO
                }else if (it.type.isCase([])){
                    r[it.name] = []
                }else if (it.type.isEnum()) {
                    r[it.name] = it.type.MIN_VALUE
                }else {
                    r[it.name] = it.type.build()
                }
            }
        }
        return r
    }
}