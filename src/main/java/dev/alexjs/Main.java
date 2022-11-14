package dev.alexjs;

import com.aparapi.Kernel;
import com.aparapi.Range;
import com.aparapi.device.Device;
import com.aparapi.internal.kernel.KernelManager;
import com.aparapi.internal.kernel.KernelPreferences;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        KernelPreferences preferences = KernelManager.instance().getDefaultPreferences();
        System.out.println("-- Devices in preferred order --");
        for (Device device : preferences.getPreferredDevices(null)) {
            System.out.println("----------");
            System.out.println(device);
        }

        Device device = KernelManager.instance().bestDevice();

        final int[] inA = new int[]{1, 1, 1, 1};
        final int[] inB = new int[]{1, 1, 1, 1};
        assert (inA.length == inB.length);

        final int[] result = new int[inA.length];
        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int i = getGlobalId();
                result[i] = inA[i] + inB[i];
            }
        };

        Range range = Range.create(result.length);
        kernel.execute(range);

        System.out.println(Arrays.toString(result));

    }

}
