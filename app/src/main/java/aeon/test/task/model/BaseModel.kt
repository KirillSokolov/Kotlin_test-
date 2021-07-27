package aeon.test.task.model

import aeon.test.task.BaseApplication
import aeon.test.task.data.Database
import aeon.test.task.data.PreferencesHelper

open class BaseModel {
    var db: Database? = null
    var ph: PreferencesHelper ? = null
    init {
        db = BaseApplication.getDataBase()
        ph= BaseApplication.getPreferenceHelper()
    }

}