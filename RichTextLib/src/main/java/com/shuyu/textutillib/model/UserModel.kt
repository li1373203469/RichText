package com.shuyu.textutillib.model

import java.io.Serializable

/**
 * 用户model
 */

class UserModel : Serializable {

    /**
     * 名字不能带@和空格
     */
    var user_name: String = ""

    var user_id: String = ""

    constructor()

    constructor(user_name: String, user_id: String) {
        this.user_name = user_name
        this.user_id = user_id
    }

    override fun toString(): String = this.user_name
}
