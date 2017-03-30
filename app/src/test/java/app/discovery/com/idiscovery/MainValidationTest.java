package app.discovery.com.idiscovery;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MainValidationTest {

    @Test
    public void testStringIsNotEmpty() {
        String text = "";
        assertThat(MainValidation.checkEmpty(text),is(true));

        text = null;
        assertThat(MainValidation.checkEmpty(text),is(true));

        text = "AAAAA";
        assertThat(MainValidation.checkEmpty(text),is(false));


    }

    @Test
    public void testStringIsDate() {
        String text = "12/12/2016";
        assertThat(MainValidation.validateDate(text),is(true));

        text = "03/08/2015";
        assertThat(MainValidation.validateDate(text),is(true));

        text = "2016/12/sasa";
        assertThat(MainValidation.validateDate(text),is(false));

        text = "AAAAA";
        assertThat(MainValidation.validateDate(text),is(false));

        text = null;
        assertThat(MainValidation.validateDate(text),is(false));
    }

    @Test
    public void testStringIsTime() {
        String text = "20:30";
        assertThat(MainValidation.validateTime(text),is(true));

        text = "08:30";
        assertThat(MainValidation.validateTime(text),is(true));

        text = "20sasa";
        assertThat(MainValidation.validateTime(text),is(false));

        text = "AAAAA";
        assertThat(MainValidation.validateTime(text),is(false));

        text = null;
        assertThat(MainValidation.validateTime(text),is(false));
    }

}
