package services;


import com.azure.core.http.rest.Response;
import com.azure.data.appconfiguration.ConfigurationClient;
import com.azure.data.appconfiguration.credentials.ConfigurationClientCredentials;
import com.azure.data.appconfiguration.models.ConfigurationSetting;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@EnableAspectJAutoProxy

public class TestAOP
{
    @SuppressWarnings("resource")
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeManager manager = context.getBean(EmployeeManager.class);

        manager.getEmployeeById(1);
        manager.createEmployee(new EmployeeDTO());

        String connectionString = "my-conn-string";
        String key = "my-key";

        ConfigurationClient client = ConfigurationClient.builder()
                .credentials(new ConfigurationClientCredentials(connectionString))
                .build();
        Response<ConfigurationSetting> response =  client.getSetting(key);
        System.out.println("settings key " + response.value());

    }
}