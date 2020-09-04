package io.rheem.giraph.plugin;

import io.rheem.core.api.Configuration;
import io.rheem.core.mapping.Mapping;
import io.rheem.core.optimizer.channels.ChannelConversion;
import io.rheem.core.platform.Platform;
import io.rheem.core.plugin.Plugin;
import io.rheem.core.util.fs.LocalFileSystem;
import io.rheem.giraph.channels.ChannelConversions;
import io.rheem.giraph.mappings.Mappings;
import io.rheem.giraph.platform.GiraphPlatform;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

/**
 * This {@link Plugin} activates default capabilities of the {@link GiraphPlatform}.
 */
public class GiraphPlugin implements Plugin{
    @Override
    public Collection<Mapping> getMappings() {
        return Mappings.ALL;
    }

    @Override
    public Collection<Platform> getRequiredPlatforms() {
        return Collections.singleton(GiraphPlatform.getInstance());
    }

    @Override
    public Collection<ChannelConversion> getChannelConversions() {
        return ChannelConversions.ALL;
    }

    @Override
    public void setProperties(Configuration configuration) {
        final File localTempDir = LocalFileSystem.findTempDir();
        if (localTempDir != null) {
            configuration.setProperty("rheem.giraph.tempdir", localTempDir.toString());
        }
    }
}
