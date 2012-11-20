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
package org.apache.cloudstack.storage.test;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.naming.ConfigurationException;

import org.apache.cloudstack.engine.subsystem.api.storage.disktype.QCOW2;
import org.apache.cloudstack.engine.subsystem.api.storage.disktype.VHD;
import org.apache.cloudstack.engine.subsystem.api.storage.disktype.VMDK;
import org.apache.cloudstack.engine.subsystem.api.storage.disktype.VolumeDiskType;
import org.apache.cloudstack.engine.subsystem.api.storage.disktype.VolumeDiskTypeHelper;
import org.apache.cloudstack.engine.subsystem.api.storage.type.VolumeTypeHelper;
import org.apache.cloudstack.storage.datastore.DefaultPrimaryDataStoreImpl;
import org.apache.cloudstack.storage.datastore.provider.DefaultPrimaryDatastoreProviderImpl;
import org.apache.cloudstack.storage.image.format.ISO;
import org.apache.cloudstack.storage.image.format.ImageFormat;
import org.apache.cloudstack.storage.image.format.ImageFormatHelper;
import org.apache.cloudstack.storage.image.format.OVA;
import org.apache.cloudstack.storage.image.format.Unknown;
import org.apache.cloudstack.storage.image.provider.ImageDataStoreProviderManager;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.mockito.Mockito.*;


import com.cloud.utils.component.ComponentInject;
import com.cloud.utils.db.DB;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/resource/storageContext.xml")
public class volumeServiceTest {
    @Inject
    ImageDataStoreProviderManager imageProviderMgr;
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void test() {
	    try {
	        
            imageProviderMgr.configure("image Provider", new HashMap<String, Object>());
            
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	//@Test
	public void test1() {
		System.out.println(VolumeTypeHelper.getType("Root"));
		System.out.println(VolumeDiskTypeHelper.getDiskType("vmdk"));
		System.out.println(ImageFormatHelper.getFormat("ova"));
		assertFalse(new VMDK().equals(new VHD()));
		VMDK vmdk = new VMDK();
		assertTrue(vmdk.equals(vmdk));
		VMDK newvmdk = new VMDK();
		assertTrue(vmdk.equals(newvmdk));
		
		ImageFormat ova = new OVA();
		ImageFormat iso = new ISO();
		assertTrue(ova.equals(new OVA()));
		assertFalse(ova.equals(iso));
		assertTrue(ImageFormatHelper.getFormat("test").equals(new Unknown()));
		
		VolumeDiskType qcow2 = new QCOW2();
		ImageFormat qcow2format = new org.apache.cloudstack.storage.image.format.QCOW2();
		assertFalse(qcow2.equals(qcow2format));
		
	}
	
	//@Test
	public void testStaticBean() {
		DefaultPrimaryDatastoreProviderImpl provider = ComponentInject.inject(DefaultPrimaryDatastoreProviderImpl.class);
		assertNotNull(provider.dataStoreDao);
		
		DefaultPrimaryDataStoreImpl dpdsi = new DefaultPrimaryDataStoreImpl(null, null, null);
		ComponentInject.inject(dpdsi);
		//assertNotNull(dpdsi.volumeDao);
	}

}
