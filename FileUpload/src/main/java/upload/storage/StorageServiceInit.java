package upload.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by mdautrey on 14/10/16.
 */
@Component
public class StorageServiceInit implements ApplicationRunner {
    @Autowired StorageService storageService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        storageService.deleteAll();
        storageService.init();

    }
}
