package org.ovirt.engine.core.common;

import java.util.Map;

import org.ovirt.engine.core.common.businessentities.ArchitectureType;
import org.ovirt.engine.core.common.businessentities.VDS;
import org.ovirt.engine.core.common.config.Config;
import org.ovirt.engine.core.common.config.ConfigValues;
import org.ovirt.engine.core.compat.Version;

/**
 * Convenience class to check if a feature is supported or not in any given version.<br>
 * Methods should be named by feature and accept version to check against.
 */
public class FeatureSupported {

    public static boolean supportedInConfig(ConfigValues feature, Version version) {
        Boolean value = Config.<Boolean> getValue(feature, version.getValue());
        if (value == null) {
            throw new IllegalArgumentException(feature.toString() + " has no value for version: " + version);
        }
        return value;
    }

    public static boolean supportedInConfig(ConfigValues feature, Version version, ArchitectureType arch) {
        Map<String, String> archOptions = Config.<Map>getValue(feature, version.getValue());
        String value = archOptions.get(arch.name());
        if (value == null) {
            value = archOptions.get(arch.getFamily().name());
        }
        return Boolean.parseBoolean(value);
    }

    public static boolean hotPlugCpu(Version version, ArchitectureType arch) {
        return supportedInConfig(ConfigValues.HotPlugCpuSupported, version, arch);
    }

    public static boolean hotUnplugCpu(Version version, ArchitectureType arch) {
        return supportedInConfig(ConfigValues.HotUnplugCpuSupported, version, arch);
    }

    public static boolean hotPlugMemory(Version version, ArchitectureType arch) {
        return supportedInConfig(ConfigValues.HotPlugMemorySupported, version, arch);
    }

    public static boolean hotUnplugMemory(Version version, ArchitectureType arch) {
        return supportedInConfig(ConfigValues.HotUnplugMemorySupported, version, arch);
    }

   /**
     * Checks if migration is supported by the given CPU architecture
     *
     * @param architecture
     *            The CPU architecture
     * @param version
     *            Compatibility version to check for.
     */
    public static boolean isMigrationSupported(ArchitectureType architecture, Version version) {
        return supportedInConfig(ConfigValues.IsMigrationSupported, version, architecture);
    }

    /**
     * Checks if High Performance VM type is supported by cluster version
     *
     * @param version
     *            Compatibility version to check for.
     */
    public static boolean isHighPerformanceTypeSupported(Version version) {
        return supportedInConfig(ConfigValues.IsHighPerformanceTypeSupported, version);
    }

    /**
     * Checks if SCSI reservations are supported by the cluster version
     *
     * @param version
     *            Compatibility version to check for.
     */
    public static boolean isScsiReservationSupported(Version version) {
        return supportedInConfig(ConfigValues.ScsiReservationSupported, version);
    }

    /**
     * Checks if memory snapshot is supported by architecture
     *
     * @param architecture
     *            The CPU architecture
     * @param version
     *            Compatibility version to check for.
     */
    public static boolean isMemorySnapshotSupportedByArchitecture(ArchitectureType architecture, Version version) {
        return supportedInConfig(ConfigValues.IsMemorySnapshotSupported, version, architecture);
    }

    /**
     * Checks if suspend is supported by architecture
     *
     * @param architecture
     *            The CPU architecture
     * @param version
     *            Compatibility version to check for.
     */
    public static boolean isSuspendSupportedByArchitecture(ArchitectureType architecture, Version version) {
        return supportedInConfig(ConfigValues.IsSuspendSupported, version, architecture);
    }

    public static boolean ipv6IscsiSupported(Version version) {
        return supportedInConfig(ConfigValues.ipv6IscsiSupported, version);
    }

    /**
     * @param version
     *            Compatibility version to check for.
     * @return <code>true</code> if gluster libgfapi access is supported for the given version
     */
    public static boolean libgfApiSupported(Version version) {
        return supportedInConfig(ConfigValues.LibgfApiSupported, version);
    }

    public static boolean isQemuimgCommitSupported(Version version) {
        return supportedInConfig(ConfigValues.QemuimgCommitSupported, version);
    }

    public static boolean isIpv6MigrationProperlyHandled(Version version) {
        return supportedInConfig(ConfigValues.Ipv6MigrationProperlyHandled, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if Managed block domain storage domain is supported for this version.
     */
    public static boolean isManagedBlockDomainSupported(Version version) {
        return supportedInConfig(ConfigValues.ManagedBlockDomainSupported, version);
    }

    /**
     * Checks if deferring file-based volume pre-allocation supported is supported by cluster version
     *
     * @param version
     *            Compatibility version to check for.
     */
    public static boolean isDeferringFileVolumePreallocationSupported(Version version) {
        return supportedInConfig(ConfigValues.IsDeferringFileVolumePreallocationSupported, version);
    }

    public static boolean isAgentChannelNamingSupported(Version version) {
        return supportedInConfig(ConfigValues.AgentChannelNamingSupported, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if VDSM trapping of guest reboot is supported.
     */
    public static boolean isDestroyOnRebootSupported(Version version) {
        return supportedInConfig(ConfigValues.DestroyOnRebootSupported, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if configuration of the resume behavior is supported.
     */
    public static boolean isResumeBehaviorSupported(Version version) {
        return supportedInConfig(ConfigValues.ResumeBehaviorSupported, version);
    }

    /**
     * Firewalld is supported for host if it supports cluster version 4.2.
     *
     * @param vds the host we are insterested in
     * @return true if host support firewalld
     */
    public static boolean isFirewalldSupported(VDS vds) {
        return vds.getSupportedClusterVersionsSet().contains(Version.v4_2);
    }

    public static boolean isReduceVolumeSupported(Version version) {
        return supportedInConfig(ConfigValues.ReduceVolumeSupported, version);
    }

    public static boolean isContentTypeSupported(Version version) {
        return supportedInConfig(ConfigValues.ContentType, version);
    }

    public static boolean isIsoOnDataDomainSupported(Version version) {
        return supportedInConfig(ConfigValues.IsoOnDataDomain, version);
    }

    public static boolean isDefaultRouteReportedByVdsm(Version version) {
        return supportedInConfig(ConfigValues.DefaultRouteReportedByVdsm, version);
    }

    /**
     * The use of libvirt's domain XML on the engine side.
     * Important: this is determined by the compatibility level of the cluster!
     * (rather than that of the VM)
     *
     * @param version Compatibility version <B>of the cluster</B> to check for.
     * @return {@code true} if the use of libvirt's domain XML is supported.
     */
    public static boolean isDomainXMLSupported(Version version) {
        return supportedInConfig(ConfigValues.DomainXML, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if getting an image ticket from vdsm is supported for this version.
     */
    public static boolean getImageTicketSupported(Version version) {
        return supportedInConfig(ConfigValues.GetImageTicketSupported, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if getting an LLDP information from vdsm is supported for this version.
     */
    public static boolean isLlldpInformationSupported(Version version) {
        return supportedInConfig(ConfigValues.LldpInformationSupported, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if getting an custom bond name is supported for this version.
     */
    public static boolean isCustomBondNameSupported(Version version) {
        return supportedInConfig(ConfigValues.CustomBondNameSupported, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if verb Host.ping2 is supported for this version.
     */
    public static boolean isPing2SupportedByVdsm(Version version) {
        return supportedInConfig(ConfigValues.Ping2SupportedByVdsm, version);
    }

    /**
     * @param version Compatibility version to check for.
     * @return {@code true} if verb Host.confirmConnectivity is supported for this version.
     */
    public static boolean isConfirmConnectivitySupportedByVdsm(Version version) {
        return supportedInConfig(ConfigValues.ConfirmConnectivitySupportedByVdsm, version);
    }

    /**
     * Checks if memory disks on different domains supported
     *
     * @param version Compatibility version to check for.
     */
    public static boolean isMemoryDisksOnDifferentDomainsSupported(Version version) {
        return supportedInConfig(ConfigValues.MemoryDisksOnDifferentDomainsSupported, version);
    }

    /**
     * Checks if BIOS Type configuration supported
     *
     * @param version Compatibility version to check for.
     */
    public static boolean isBiosTypeSupported(Version version) {
        return supportedInConfig(ConfigValues.BiosTypeSupported, version);
    }

    /**
     * Check if aio=native should be used for Gluster storage
     * instead of threads
     *
     * @param version Compatibility version to check for.
     */
    public static boolean useNativeIOForGluster(Version version) {
        return supportedInConfig(ConfigValues.UseNativeIOForGluster, version);
    }

    /**
     * @param version
     *            Check if the Hyper-V KVM enlightenments are supported.
     * @return <code>true</code> if Hyper-V KVM enlightenments are supported for the given version.
     */
    public static boolean hyperVSynicStimerSupported(Version version) {
        return supportedInConfig(ConfigValues.HyperVSynicStimerSupported, version);
    }

    /**
     * Check if vGPU placement is supported
     *
     * @param version Compatibility version to check for.
     */
    public static boolean isVgpuPlacementSupported(Version version) {
        return supportedInConfig(ConfigValues.VgpuPlacementSupported, version);
    }

}
