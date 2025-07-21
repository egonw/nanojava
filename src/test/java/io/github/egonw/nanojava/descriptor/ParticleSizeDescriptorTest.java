/* Copyright (C) 2011-2025  Egon Willighagen <egonw@users.sf.net>
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package io.github.egonw.nanojava.descriptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openscience.cdk.qsar.DescriptorValue;
import org.openscience.cdk.qsar.result.DoubleResult;
import org.openscience.cdk.qsar.result.IDescriptorResult;

import com.github.jqudt.onto.units.LengthUnit;

import io.github.egonw.nanojava.data.Material;
import io.github.egonw.nanojava.data.MaterialType;
import io.github.egonw.nanojava.data.measurement.EndPoints;
import io.github.egonw.nanojava.data.measurement.MeasurementRange;
import io.github.egonw.nanojava.data.measurement.MeasurementValue;

public class ParticleSizeDescriptorTest
extends MaterialDescriptorTest {

    @BeforeEach
    public void setUp() throws Exception {
        setDescriptor(ParticleSizeDescriptor.class);
    }

    @Test
    public void testCalculate_Range() throws Exception {
        Material material = new Material(MaterialType.METALOXIDE);
        material.setSize(new MeasurementRange(EndPoints.SIZE, 10, 20, LengthUnit.NM));
        DescriptorValue value = descriptor.calculate(material);
        assertNotNull(value);
        IDescriptorResult result = value.getValue();
        assertNotNull(result);
        assertEquals(15.0, ((DoubleResult)result).doubleValue(), 0.0001);
    }

    @Test
    public void testCalculate_Value() throws Exception {
        Material material = new Material(MaterialType.METALOXIDE);
        material.setSize(new MeasurementValue(EndPoints.SIZE, 30, 5, LengthUnit.NM));
        DescriptorValue value = descriptor.calculate(material);
        assertNotNull(value);
        IDescriptorResult result = value.getValue();
        assertNotNull(result);
        assertEquals(30.0, ((DoubleResult)result).doubleValue(), 0.0001);
    }

}
