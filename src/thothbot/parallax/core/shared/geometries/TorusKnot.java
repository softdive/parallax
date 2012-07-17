/*
 * Copyright 2012 Alex Usachev, thothbot@gmail.com
 * 
 * This file based on the JavaScript source file of the THREE.JS project, 
 * licensed under MIT License.
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

package thothbot.parallax.core.shared.geometries;

import java.util.Arrays;

import thothbot.parallax.core.shared.Log;
import thothbot.parallax.core.shared.core.Face4;
import thothbot.parallax.core.shared.core.Geometry;
import thothbot.parallax.core.shared.core.UVf;
import thothbot.parallax.core.shared.core.Vector3f;

import com.google.gwt.core.client.GWT;

public final class TorusKnot extends Geometry
{

	public TorusKnot() 
	{
		this(200, 40, 64, 8);
	}
	
	public TorusKnot(int radius, int tube, int segmentsR, int segmentsT)
	{
		this(radius, tube, segmentsR, segmentsT, 2, 3, 1);
	}
			
	public TorusKnot(int radius, int tube, int segmentsR, int segmentsT, int p, int q, int heightScale ) 
	{
		super();
		
		int[][] grid = new int[segmentsR][];

		Vector3f tang = new Vector3f();
		Vector3f n = new Vector3f();
		Vector3f bitan = new Vector3f();

		for ( int i = 0; i < segmentsR; ++ i )  	
		{
			grid[ i ] = new int[segmentsT];

			for ( int j = 0; j < segmentsT; ++ j ) 
			{
				double u = i / (double)segmentsR * 2.0 * p * Math.PI;
				double v = j / (double)segmentsT * 2.0 * Math.PI;

				Vector3f p1 = getPos( u,        v, q, p, radius, heightScale );
				Vector3f p2 = getPos( u + 0.01, v, q, p, radius, heightScale );
				
				tang.sub( p2, p1 );
				n.add( p2, p1 );

				bitan.cross( tang, n );
				n.cross( bitan, tang );
				bitan.normalize();
				n.normalize();

				float cx = (float) -(tube * Math.cos( v )); // TODO: Hack: Negating it so it faces outside.
				float cy = (float) (tube * Math.sin( v ));

				p1.addX(cx * n.getX() + cy * bitan.getX());
				p1.addY(cx * n.getY() + cy * bitan.getY());
				p1.addZ(cx * n.getZ() + cy * bitan.getZ());

				grid[ i ][ j ] = vert( p1.getX(), p1.getY(), p1.getZ() );
			}
		}
		
		for ( int i = 0; i < segmentsR; ++ i ) 
		{
			for ( int j = 0; j < segmentsT; ++ j ) 
			{
				int ip = ( i + 1 ) % segmentsR;
				int jp = ( j + 1 ) % segmentsT;

				int a = grid[ i ][ j ];
				int b = grid[ ip ][ j ];
				int c = grid[ ip ][ jp ];
				int d = grid[ i ][ jp ];

				UVf uva = new UVf(            i / (float)segmentsR,            j / (float)segmentsT );
				UVf uvb = new UVf( ( i + 1.0f ) / (float)segmentsR,            j / (float)segmentsT );
				UVf uvc = new UVf( ( i + 1.0f ) / (float)segmentsR, ( j + 1.0f ) / (float)segmentsT );
				UVf uvd = new UVf(            i / (float)segmentsR, ( j + 1.0f ) / (float)segmentsT );

				this.faces.add( new Face4( a, b, c, d ) );
				this.faceVertexUvs.get( 0 ).add( Arrays.asList( uva, uvb, uvc, uvd ) );
			}
		}

		this.computeCentroids();
		this.computeFaceNormals(false);
		this.computeVertexNormals();
	}
	
	private int vert( float x, float y, float z ) 
	{
		this.vertices.add( new Vector3f( x, y, z ) );
		return this.vertices.size() - 1;
	}

	private Vector3f getPos( double u, double v, int in_q, int in_p, int radius, int heightScale ) 
	{
		double cu = Math.cos( u );
		double cv = Math.cos( v );
		double su = Math.sin( u );
		double quOverP = (in_q / (double)in_p * u);
		double cs = Math.cos( quOverP );

		return new Vector3f( 
			(float)(radius * ( 2.0f + cs ) * cu * 0.5f),
			(float)(radius * ( 2.0f + cs ) * su * 0.5f),
			(float)(heightScale * radius * Math.sin( quOverP ) * 0.5f)
		);
	}
}