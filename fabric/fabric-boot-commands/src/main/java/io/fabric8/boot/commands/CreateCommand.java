/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.boot.commands;

import io.fabric8.api.ZooKeeperClusterBootstrap;
import io.fabric8.api.ZooKeeperClusterService;
import io.fabric8.api.scr.ValidatingReference;
import io.fabric8.boot.commands.service.CreateAvailable;

import java.util.Map;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.basic.AbstractCommand;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.service.command.Function;
import org.osgi.framework.BundleContext;

@Command(name = CreateCommand.FUNCTION_VALUE, scope = CreateCommand.SCOPE_VALUE, description = CreateCommand.DESCRIPTION, detailedDescription = "classpath:create.txt")
@Component(immediate = true, policy = ConfigurationPolicy.OPTIONAL)
@Service({ Function.class, CreateAvailable.class })
@org.apache.felix.scr.annotations.Properties({
        @Property(name = "osgi.command.scope", value = CreateCommand.SCOPE_VALUE),
        @Property(name = "osgi.command.function", value = CreateCommand.FUNCTION_VALUE)
})
public final class CreateCommand extends AbstractCommand implements CreateAvailable {

    public static final String SCOPE_VALUE = "fabric";
    public static final String FUNCTION_VALUE =  "create";
    public static final String DESCRIPTION = "Creates a new fabric ensemble (ZooKeeper ensemble) and imports fabric profiles";

    @Reference(referenceInterface = ZooKeeperClusterBootstrap.class, bind = "bindBootstrap", unbind = "unbindBootstrap")
    private final ValidatingReference<ZooKeeperClusterBootstrap> bootstrap = new ValidatingReference<ZooKeeperClusterBootstrap>();
    @Reference(referenceInterface = ZooKeeperClusterService.class, bind = "bindService", unbind = "unbindService", cardinality = ReferenceCardinality.OPTIONAL_UNARY, policy = ReferencePolicy.DYNAMIC)
    private final ValidatingReference<ZooKeeperClusterService> service = new ValidatingReference<ZooKeeperClusterService>();

    private BundleContext bundleContext;

    @Activate
    void activate(BundleContext bundleContext, Map<String, ?> props) {
        this.bundleContext = bundleContext;
    }

    @Deactivate
    void deactivate() {
    }

    @Override
    public Action createNewAction() {
        return new CreateAction(bundleContext, bootstrap.get());
    }

    void bindBootstrap(ZooKeeperClusterBootstrap bootstrap) {
        this.bootstrap.bind(bootstrap);
    }

    void unbindBootstrap(ZooKeeperClusterBootstrap bootstrap) {
        this.bootstrap.unbind(bootstrap);
    }

    void bindService(ZooKeeperClusterService service) {
        this.service.bind(service);
    }

    void unbindService(ZooKeeperClusterService service) {
        this.service.unbind(service);
    }
}
