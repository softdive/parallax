/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file is part of Parallax project.
 * 
 * Parallax is free software: you can redistribute it and/or modify it 
 * under the terms of the Creative Commons Attribution 3.0 Unported License.
 * 
 * Parallax is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the Creative Commons Attribution 
 * 3.0 Unported License. for more details.
 * 
 * You should have received a copy of the the Creative Commons Attribution 
 * 3.0 Unported License along with Parallax. 
 * If not, see http://creativecommons.org/licenses/by/3.0/.
 */

package thothbot.parallax.core.shared.scenes;

import java.util.Map;

import thothbot.parallax.core.client.shaders.Uniform;
import thothbot.parallax.core.shared.core.Color;

/**
 * Abstract realization of FogAbstract. This class implements color
 * property only.
 * 
 * @author thothbot
 *
 */
public abstract class FogAbstract
{
	private Color color;
	
	/**
	 * This default constructor will make abstract FogAbstract with
	 * defined color
	 *  
	 * @param hex the color in HEX format
	 */
	public FogAbstract(int hex)
	{
		this.color = new Color(hex);
	}

	/**
	 * Set color for the FogAbstract
	 * 
	 * @param color the color instance
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}

	/**
	 * Get color of the FogAbstract
	 * 
	 * @return the color instance
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * The method refreshes uniforms for the fog
	 * 
	 * @param uniforms the map of uniforms
	 */
	public void refreshUniforms(Map<String, Uniform> uniforms) 
	{
		uniforms.get("fogColor").setValue( getColor() );
	}
}
