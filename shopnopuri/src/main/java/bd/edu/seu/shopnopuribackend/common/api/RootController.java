package bd.edu.seu.shopnopuribackend.common.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



//for see universal reult amar app ki cholche ki cholche na dekhar jonno 
@RestController
public class RootController {

    @GetMapping("/")
    public String root() {
        return "ShopnoPuri Backend is running";
    }
}
