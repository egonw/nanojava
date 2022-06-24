/* Copyright (C) 2011,2013  Egon Willighagen <egonw@users.sf.net>
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

public enum MaterialType {

    METAL("metal", ""),
    METALOXIDE("metal oxide", ""),
    CARBONNANOTUBE("carbon nanotube", ""),
    NANOTUBE("nanotube", ""),
    GRAPHENE("graphene", "");

    String label;
    // future NPO support
    String uri;

    MaterialType(String label, String uri) {
    	this.label = label;
        this.uri = uri;
    }

    public String getLabel() {
    	return this.label;
    }

    public String getURI() {
    	return this.uri;
    }
}
