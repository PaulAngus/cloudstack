/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cloudstack.storage.datastore;

import java.util.List;

import org.apache.cloudstack.engine.cloud.entity.api.VolumeEntity;
import org.apache.cloudstack.engine.subsystem.api.storage.EndPoint;
import org.apache.cloudstack.engine.subsystem.api.storage.PrimaryDataStoreInfo;
import org.apache.cloudstack.engine.subsystem.api.storage.VolumeInfo;
import org.apache.cloudstack.engine.subsystem.api.storage.disktype.VolumeDiskType;
import org.apache.cloudstack.storage.image.TemplateInfo;
import org.apache.cloudstack.storage.image.TemplateObject;
import org.apache.cloudstack.storage.volume.TemplateOnPrimaryDataStoreInfo;
import org.apache.cloudstack.storage.volume.VolumeObject;

public interface PrimaryDataStore extends PrimaryDataStoreInfo {
    VolumeInfo getVolume(long id);

    List<VolumeInfo> getVolumes();

    boolean deleteVolume(long id);

    VolumeInfo createVolume(VolumeInfo vo, VolumeDiskType diskType);

    VolumeInfo createVoluemFromBaseImage(VolumeInfo volume, TemplateOnPrimaryDataStoreInfo templateStore);

    List<EndPoint> getEndPoints();

    PrimaryDataStoreInfo getDataStoreInfo();

    boolean exists(VolumeInfo vi);

    boolean templateExists(TemplateInfo template);

    TemplateOnPrimaryDataStoreInfo getTemplate(TemplateInfo template);

    boolean installTemplate(TemplateOnPrimaryDataStoreInfo template);

    VolumeDiskType getDefaultDiskType();

}
