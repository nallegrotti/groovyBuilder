import java.lang.reflect.Modifier;

class MockBuilder{

    public static Object build(Class c, model){
        def r = c.newInstance()
        def doNotTouch = ['metaClass', '$staticClassInfo', '__$stMC', '$callSiteArray']

        model.each{ k, v -> 
            r[k] = v
            doNotTouch << k
        }

        c.declaredFields.each{
            if (!(it.name in doNotTouch) && !(it.modifiers & Modifier.FINAL)){
                if (it.type == String){
                    r[it.name] = it.name
                }else if (it.type in [long, int]){
                    r[it.name] = 0
                }else if (it.type.isCase([:])){
                    r[it.name] = [:]
                }else if (it.type.isCase(0)){
                    r[it.name] = 0
                }else if (it.type.isCase(0L)){
                    r[it.name] = 0L
                }else if (it.type.isCase(0D)){
                    r[it.name] = 0D
                }else if (it.type.isCase(false)){
                    r[it.name] = false
                }else if (it.type == BigDecimal){
                    r[it.name] = BigDecimal.ZERO
                }else if (it.type.isCase([])){
                    r[it.name] = []
                }else if (it.type.isEnum()) {
                    r[it.name] = it.type.values()[0]
                }else {
                    r[it.name] = it.type.build()
                }
            }
        }
        return r
    }
}