/*
 * Copyright (C) 2016 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.subcomponent;

import static com.google.common.truth.Truth.assertThat;

import dagger.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.subcomponent.UsesModuleSubcomponents.ParentIncludesSubcomponentTransitively;

/** Tests for {@link Module#subcomponents()}. */
@RunWith(JUnit4.class)
public class ModuleWithSubcomponentsTest {

  @Test
  public void subcomponentFromModules() {
    UsesModuleSubcomponents parent = DaggerUsesModuleSubcomponents.create();
    assertThat(parent.strings()).containsExactly("from parent");
    assertThat(parent.usesChild().strings).containsExactly("from parent", "from child");
  }

  @Test
  public void subcomponentFromModules_transitively() {
    ParentIncludesSubcomponentTransitively parent =
        DaggerUsesModuleSubcomponents_ParentIncludesSubcomponentTransitively.create();
    assertThat(parent.strings()).containsExactly("from parent");
    assertThat(parent.usesChild().strings).containsExactly("from parent", "from child");
  }
}
