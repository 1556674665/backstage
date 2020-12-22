package guanwang.s78;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("guanwang.s78.Dao")
public class S78Application {

    public static void main(String[] args) {
        SpringApplication.run(S78Application.class, args);
    }

}
