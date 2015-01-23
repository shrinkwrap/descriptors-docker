/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.api.docker.instruction;

import java.util.List;

/**
 * The <code>RUN</code> instruction will execute any commands in a new layer on top of the current image and commit the
 * results. The resulting committed image will be used for the next step in the Dockerfile.
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public interface RunInstruction extends DockerInstruction
{
   RunInstruction parameters(String... commands);

   List<String> getParameters();
}
