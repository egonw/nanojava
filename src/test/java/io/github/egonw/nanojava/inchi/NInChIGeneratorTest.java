/* Copyright (C) 2012-2025  Egon Willighagen <egonw@users.sf.net>
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
package io.github.egonw.nanojava.inchi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.github.jqudt.onto.units.LengthUnit;

import io.github.egonw.nanojava.data.Material;
import io.github.egonw.nanojava.data.MaterialBuilder;
import io.github.egonw.nanojava.data.measurement.EndPoints;
import io.github.egonw.nanojava.data.measurement.ErrorlessMeasurementValue;

public class NInChIGeneratorTest {

	@Test
	public void generate() throws Exception {
		Material material = MaterialBuilder.type("METALOXIDE")
			.label("silica nanoparticles with gold coating")
			.componentFromSMILES(1, "O=[Si]=O", "SPHERE", "AMORPHOUS", new ErrorlessMeasurementValue(EndPoints.DIAMETER, 20, LengthUnit.NM))
			.componentFromSMILES(2, "[Au]", "SHELL", new ErrorlessMeasurementValue(EndPoints.THICKNESS, 2, LengthUnit.NM))
			.asMaterial();

		String nanoInChI = NInChIGenerator.generator(material);
		assertNotNull(nanoInChI);
	}
}
