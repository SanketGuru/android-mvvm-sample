package sanketguru.com.sample

import java.util.Date

/**
 * Created by Sanket Gurav on 11/28/2017.
 */

class UserDetails(fName: String, lName: String, dob: Date) {
     var fName = ""
     var lName = ""
    var dob = Date()

    init {
        this.fName = fName
        this.lName = lName
        this.dob = dob
    }
}
