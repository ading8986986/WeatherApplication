package android.demo.weatherapplication.feature.contact

import android.demo.weatherapplication.feature.contact.model.ContactForm
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ContactFormUnitTest {
    val contactForm = ContactForm()

    @Test
    fun name_isCorrect() {
        contactForm.setName("Valid Name")
        assertFalse(contactForm.verifyName().isError)
    }

    @Test
    fun name_containsNumber() {
        contactForm.setName("InValid Name2")
        assertTrue(contactForm.verifyName().isError)
        assertEquals(
            ContactForm.NameError.CONTAIN_NUMBERS.errorMsg,
            contactForm.verifyName().errorMsg
        )
    }

    @Test
    fun name_isShort() {
        contactForm.setName("Val")
        assertTrue(contactForm.verifyName().isError)
        assertEquals(
            ContactForm.NameError.NAME_TOO_SHORT.errorMsg,
            contactForm.verifyName().errorMsg
        )
    }

    @Test
    fun email_isCorrect() {
        val emails = arrayOf(
            "alice@example.com",
            "alice.bob@example.co.in",
            "alice1@example.me.org",
            "alice_bob@example.com",
            "alice-bob@example.com"
        )
        for (email in emails) {
            contactForm.setEmail(email)
            assertFalse(
                "$email should be correct",
                contactForm.verifyEmail().isError
            )
        }
    }

    @Test
    fun email_isWrongFormat() {
        val emails = arrayOf(
            "@example.com",
            "alice&example.com",
            "alice#@example.me.org"
        )
        for (email in emails) {
            contactForm.setEmail(email)
            assertTrue(contactForm.verifyEmail().isError)
            assertEquals(
                "$email should be incorrect",
                ContactForm.EmailError.WRONG_FORMAT.errorMsg,
                contactForm.verifyEmail().errorMsg
            )
        }
    }

    @Test
    fun phoneNumber_isCorrect() {
        val phoneNumbers = arrayOf(
            "6475723441",
            "6475323422",
            "4165323422"
        )
        for (number in phoneNumbers) {
            contactForm.setPhoneNumber(number)
            assertFalse("$number should be correct", contactForm.verifyPhone().isError)
        }
    }

    @Test
    fun phoneNumber_isWrongFormat() {
        val phoneNumbers = arrayOf(
            "147572344111",
            "416132342",
            "1475723441",
            "6975323422",
            "4161323422",
            "6475723*41",
            "6475#23441"
        )
        for (number in phoneNumbers) {
            contactForm.setPhoneNumber(number)
            assertTrue(contactForm.verifyPhone().isError)
            assertEquals(
                "$number should be incorrect",
                ContactForm.PhoneNumberError.WRONG_FORMAT.errorMsg,
                contactForm.verifyPhone().errorMsg
            )
        }
    }

    @Test
    fun submitButton_isEnabled(){
        setValidContact()
        contactForm.verifyName()
        contactForm.verifyEmail()
        contactForm.verifyPhone()
        assertTrue(contactForm.isContactValid())
    }

    @Test
    fun submitButton_isDisabled(){
        setValidContact()
        //invalid name
        contactForm.setName("InValid Name2")
        contactForm.verifyName()
        assertFalse(contactForm.isContactValid())
        //invalid email
        setValidContact()
        contactForm.setEmail("examplemail.com")
        contactForm.verifyEmail()
        assertFalse(contactForm.isContactValid())
        //invalid phone
        setValidContact()
        contactForm.setPhoneNumber("147572344111")
        contactForm.verifyPhone()
        assertFalse(contactForm.isContactValid())
    }

    private fun setValidContact(){
        contactForm.setName("Valid Name")
        contactForm.setEmail("example@gmail.com")
        contactForm.setPhoneNumber("6475723443")
    }
}