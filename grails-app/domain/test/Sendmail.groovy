package test

import java.util.Date;

class Sendmail {
    String emailAdd
	String title
	String content
	Date sendTime
	static mapping = {
		columns {
		content type: 'text'
		}
		}
    static constraints = {
		emailAdd(maxLength: 200, blank: false, email: true)
		title(maxLength: 200, blank: false)
		content(blank: false)
    }
	def beforeInsert = {
		sendTime = new Date() ;
		}
}
