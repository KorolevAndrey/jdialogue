/*******************************************************************************
 * DialogueEditor
 * Copyright (C) 2013-2014 Pawel Pastuszak
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package pl.kotcrab.jdialogue.editor.components.types;

import pl.kotcrab.jdialogue.editor.Assets;
import pl.kotcrab.jdialogue.editor.KotcrabText;
import pl.kotcrab.jdialogue.editor.components.ComponentTableModel;
import pl.kotcrab.jdialogue.editor.components.DComponent;
import pl.kotcrab.jdialogue.editor.project.PCallback;

public class CallbackCheckComponent extends DComponent {
	public CallbackCheckComponent (int x, int y) {
		super("Cb. Check", x, y, 1, 2);
		tableModel = new ComponentTableModel(
				//@formatter:off
			new Object[][]
				{
				    {"Callback", new PCallback(0, "Default callback")},
				}
			//@formatter:on
		);
	}

	@Override
	public KotcrabText[] provideInputLabels () {
		return new KotcrabText[]{new KotcrabText(Assets.consolasFont, "In", false, 0, 0)};
	}

	@Override
	public KotcrabText[] provideOutputsLabels () {
		return new KotcrabText[]{new KotcrabText(Assets.consolasFont, "True", false, 0, 0),
				new KotcrabText(Assets.consolasFont, "False", false, 0, 0)};
	}
}
