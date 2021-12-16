package aeon.test.task.model

import aeon.test.task.restapi.response.BaseData

interface Model {
    fun create()
    fun update()
    fun convertFromResponse(data: List<BaseData>)
}