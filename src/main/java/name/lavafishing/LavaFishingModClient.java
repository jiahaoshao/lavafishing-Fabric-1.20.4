package name.lavafishing;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LavaFishingModClient extends LavaFishing implements ClientModInitializer {
    @Override
    public void onInitializeClient(){

    }
}
