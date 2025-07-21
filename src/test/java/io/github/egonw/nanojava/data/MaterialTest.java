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
package io.github.egonw.nanojava.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import io.github.egonw.nanojava.data.measurement.EndPoints;
import io.github.egonw.nanojava.data.measurement.ErrorlessMeasurementValue;
import io.github.egonw.nanojava.io.CDKDeserializer;
import io.github.egonw.nanojava.io.CDKSerializer;

import org.junit.jupiter.api.Test;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.xmlcml.cml.element.CMLMoleculeList;

import com.github.jqudt.onto.units.LengthUnit;

public class MaterialTest {

    @Test
    public void testAlwaysReturnsLabelList() throws Exception {
        Material nm = new Material("GRAPHENE");
        assertNotNull(nm.getLabels());
    }

    @Test
    public void testChemicalCompisitionWhenEmpty() throws Exception {
        Material nm = new Material("GRAPHENE");
        assertNull(nm.getChemicalComposition());
        assertNotNull(nm.getType());
    }

    @Test
    public void testDefaultConstructor() throws Exception {
        Material nm = new Material();
        assertNull(nm.getType());
    }

    @Test
    public void testLabels() throws Exception {
        Material nm = new Material("GRAPHENE");
        List<String> labels = new ArrayList<String>();
        labels.add("NM1"); labels.add("CeO2-15");
        nm.setLabels(labels);
        assertNotNull(nm.getLabels());
        assertEquals(2, nm.getLabels().size());
        assertTrue(nm.getLabels().contains("NM1"));
    }

    @Test
    public void testMultipleSizes() throws Exception {
        Material nm = new Material("GRAPHENE");
        nm.addCharacterization(new ErrorlessMeasurementValue(EndPoints.DIAMETER_TEM, 20.0, LengthUnit.NM));
        nm.addCharacterization(new ErrorlessMeasurementValue(EndPoints.DIAMETER_DLS, 55.0, LengthUnit.NM));
        assertNotNull(nm.getCharacterizations());
        assertEquals(2, nm.getCharacterizations().size());
    }

	@Test
	public void figureEightLeft() throws Exception {
		Material material = MaterialBuilder.type("METALOXIDE")
			.label("silica nanoparticles with gold coating")
			.componentFromSMILES(1, "O=[Si]=O", "SPHERE")
			.componentFromSMILES(2, "[Au]", "SHELL")
			.asMaterial();

		CMLMoleculeList cmlMaterial = CDKSerializer.toCML(material);
		assertNotNull(cmlMaterial);
		Material roundTripped = CDKDeserializer.fromCML(cmlMaterial);
		assertNotNull(roundTripped);
        assertNotNull(roundTripped.getLabels());
        assertEquals(1, roundTripped.getLabels().size());
        assertEquals(2, roundTripped.getAtomContainerCount());
        for (IAtomContainer container : roundTripped.atomContainers()) {
        	assertNotNull(container.getProperty(Material.ORDER));
        }
	}
}
