package bd.edu.seu.shopnopuribackend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TempHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("Admin@123"));
    }
}
