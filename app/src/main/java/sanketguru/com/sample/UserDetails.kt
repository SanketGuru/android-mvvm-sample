package sanketguru.com.sample

import java.util.Date

/**
 * Created by Sanket Gurav on 11/28/2017.
 */

class UserDetails(fName: String, lName: String, dob: Date) {
    private var fName = ""
    private var lName = ""
    private var dob = Date()

    init {
        this.fName = fName
        this.lName = lName
        this.dob = dob
    }
}
