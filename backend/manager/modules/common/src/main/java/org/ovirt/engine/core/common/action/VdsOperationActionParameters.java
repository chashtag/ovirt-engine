package org.ovirt.engine.core.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.ovirt.engine.core.common.businessentities.HostedEngineDeployConfiguration;
import org.ovirt.engine.core.common.businessentities.Label;
import org.ovirt.engine.core.common.businessentities.VDS;
import org.ovirt.engine.core.common.businessentities.VdsStatic;
import org.ovirt.engine.core.common.scheduling.AffinityGroup;

public class VdsOperationActionParameters extends VdsActionParameters {
    private static final long serialVersionUID = 4681434926930223395L;

    @Valid
    private VdsStatic vdsStatic;
    private String password;
    /**
     * Since version 4.1.2 of the engine when a host is newly added or reinstalled we override the host firewall
     * definitions by default.
     */
    private boolean overrideFirewall = true;
    private boolean activateHost;
    private boolean enableSerialConsole;
    private AuthenticationMethod authMethod;
    private String networkMappings;
    private HostedEngineDeployConfiguration hostedEngineDeployConfiguration;
    private List<AffinityGroup> affinityGroups;
    private List<Label> affinityLabels;

    public enum AuthenticationMethod {
        Password(0),
        PublicKey(1);

        private int intValue;
        private static Map<Integer, AuthenticationMethod> mappings;

        static {
            mappings = new HashMap<>();
            for (AuthenticationMethod error : values()) {
                mappings.put(error.getValue(), error);
            }
        }

        private AuthenticationMethod(int value) {
            intValue = value;
        }

        public int getValue() {
            return intValue;
        }

        public static AuthenticationMethod forValue(int value) {
            return mappings.get(value);
        }
    }

    public VdsOperationActionParameters(VdsStatic vdsStaticVal, String passwordVal) {
        super(vdsStaticVal.getId());
        vdsStatic = vdsStaticVal;
        password = passwordVal;
        authMethod = AuthenticationMethod.Password;
        enableSerialConsole = true;
        affinityLabels = new ArrayList<>();
    }

    public VdsOperationActionParameters(VdsStatic vdsStatic) {
        this(vdsStatic, null);
    }

    public VdsOperationActionParameters() {
        authMethod = AuthenticationMethod.Password;
        enableSerialConsole = true;
        affinityLabels = new ArrayList<>();
    }

    public VdsStatic getVdsStaticData() {
        return vdsStatic;
    }

    @ShouldNotBeLogged
    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        password = value;
    }

    public void setAuthMethod(AuthenticationMethod value) {
        authMethod = value;
    }

    public AuthenticationMethod getAuthMethod() {
        return authMethod;
    }

    // Deprecated to keep old api with root password
    @ShouldNotBeLogged
    public String getRootPassword() {
        return password;
    }

    public void setRootPassword(String value) {
        password = value;
    }

    public VDS getvds() {
        VDS vds = new VDS();
        vds.setStaticData(vdsStatic);
        return vds;
    }

    public void setvds(VDS value) {
        vdsStatic = value.getStaticData();
    }

    public void setOverrideFirewall(boolean overrideFirewall) {
        this.overrideFirewall = overrideFirewall;
    }

    public boolean getOverrideFirewall() {
        return overrideFirewall;
    }

    public void setActivateHost(boolean activateHost) {
        this.activateHost = activateHost;
    }

    public boolean getActivateHost() {
        return activateHost;
    }

    public void setEnableSerialConsole(boolean enableSerialConsole) {
        this.enableSerialConsole = enableSerialConsole;
    }

    public boolean getEnableSerialConsole() {
        return enableSerialConsole;
    }

    public HostedEngineDeployConfiguration getHostedEngineDeployConfiguration() {
        return hostedEngineDeployConfiguration;
    }

    public void setHostedEngineDeployConfiguration(HostedEngineDeployConfiguration hostedEngineDeployConfiguration) {
        this.hostedEngineDeployConfiguration = hostedEngineDeployConfiguration;
    }

    public List<AffinityGroup> getAffinityGroups() {
        return affinityGroups;
    }

    public void setAffinityGroups(List<AffinityGroup> affinityGroups) {
        this.affinityGroups = affinityGroups;
    }

    public List<Label> getAffinityLabels() {
        return affinityLabels;
    }

    public void setAffinityLabels(List<Label> affinityLabels) {
        this.affinityLabels = affinityLabels;
    }
}
