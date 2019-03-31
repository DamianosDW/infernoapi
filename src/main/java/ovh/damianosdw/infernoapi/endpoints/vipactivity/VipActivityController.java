package ovh.damianosdw.infernoapi.endpoints.vipactivity;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infernoapi")
@AllArgsConstructor
public class VipActivityController
{
    private final VipActivityModuleRepository vipActivityModuleRepository;
    private final VipActivityCheckRepository vipActivityCheckRepository;


}
