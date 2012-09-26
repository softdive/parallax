/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file is part of Parallax project.
 * 
 * Parallax is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 * 
 * Parallax is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * Parallax. If not, see http://www.gnu.org/licenses/.
 */

package thothbot.parallax.core.client.gl2.enums;

/**
 * GL2 Texture Parameter name flags.
 * 
 * @author thothbot
 *
 */
public enum TextureParameterName 
{
	TEXTURE_MAG_FILTER(GLEnum.TEXTURE_MAG_FILTER),
	TEXTURE_MIN_FILTER(GLEnum.TEXTURE_MIN_FILTER),
	TEXTURE_WRAP_S(GLEnum.TEXTURE_WRAP_S),
	TEXTURE_WRAP_T(GLEnum.TEXTURE_WRAP_T),
	
	TEXTURE_MAX_ANISOTROPY_EXT(EXT_TextureFilterAnisotropic.TEXTURE_MAX_ANISOTROPY_EXT),
	MAX_TEXTURE_MAX_ANISOTROPY_EXT(EXT_TextureFilterAnisotropic.MAX_TEXTURE_MAX_ANISOTROPY_EXT);

	private final int value;

	private TextureParameterName(EXT_TextureFilterAnisotropic GLEnum) 
	{
		this.value = GLEnum.getValue();
	}
	
	private TextureParameterName(GLEnum GLEnum) 
	{
		this.value = GLEnum.getValue();
	}
	
	/**
	 * Gets the enum's numerical value.
	 */
	public int getValue() 
	{
		return value;
	}
}
