package pe.edu.upeu.registro.registromvc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import pe.edu.upeu.registro.registromvc.bean.Person;
import pe.edu.upeu.registro.registromvc.dao.PersonDAO;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        PersonDAO dao = new PersonDAO(appContext);
        Person person = dao.findPersonById("2");

        assertEquals("Omar", person.getName());

        assertEquals("pe.edu.upeu.registro.registromvc", appContext.getPackageName());
    }
}
