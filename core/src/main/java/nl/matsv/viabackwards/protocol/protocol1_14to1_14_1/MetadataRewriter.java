package nl.matsv.viabackwards.protocol.protocol1_14to1_14_1;

import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.api.entities.Entity1_14Types;
import us.myles.ViaVersion.api.minecraft.metadata.Metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Neuhaus on 15.05.2019 for the Project ViaBackwardsFoorcee.
 */
public class MetadataRewriter {

    public static void handleMetadata(int entityId, Entity1_14Types.EntityType type, List<Metadata> metadatas, UserConnection connection)  {
        if (type == null) return;

        for (Metadata metadata : new ArrayList<>(metadatas)) {
            try {
                if (type.is(Entity1_14Types.EntityType.VILLAGER) || type.is(Entity1_14Types.EntityType.WANDERING_TRADER)) {
                    if (metadata.getId() >= 16) {
                        metadata.setId(metadata.getId() - 1);
                    }
                }
            } catch (Exception e) {
                metadatas.remove(metadata);
                if (!Via.getConfig().isSuppressMetadataErrors() || Via.getManager().isDebug()) {
                    Via.getPlatform().getLogger().warning("An error occurred with entity metadata handler");
                    Via.getPlatform().getLogger().warning("Metadata: " + metadata);
                    e.printStackTrace();
                }
            }
        }
    }

}
