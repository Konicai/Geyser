/*
 * Copyright (c) 2024 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.command;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.geysermc.geyser.api.event.lifecycle.GeyserRegisterPermissionsEvent;
import org.geysermc.geyser.api.util.TriState;

import java.util.Map;
import java.util.Objects;

public class GeyserPermissionRegistrationEventImpl implements GeyserRegisterPermissionsEvent {

    protected final Map<String, TriState> permissionDefaults = new Object2ObjectOpenHashMap<>();
    private boolean canRegister = true;

    @Override
    public void register(@NonNull String permission, @NonNull TriState defaultValue) {
        if (!canRegister) {
            throw new IllegalArgumentException("Unable to register permissions after they've been collected!");
        }
        Objects.requireNonNull(permission, "permission must be non null");
        Objects.requireNonNull(defaultValue, "permission default must be non null!");
        if (permission.isBlank()) {
            return;
        }

        permissionDefaults.put(permission, defaultValue);
    }

    public Map<String, TriState> getPermissionDefaults() {
        canRegister = false;
        return permissionDefaults;
    }
}
