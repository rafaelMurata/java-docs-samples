/*
 * Copyright 2024 Google LLC
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

package vtwo.muteconfig;

// [START securitycenter_set_mute_v2]

import com.google.cloud.securitycenter.v2.Finding;
import com.google.cloud.securitycenter.v2.Finding.Mute;
import com.google.cloud.securitycenter.v2.SecurityCenterClient;
import com.google.cloud.securitycenter.v2.SetMuteRequest;
import java.io.IOException;

public class SetMuteFinding {

  public static void main(String[] args) throws IOException {
    // TODO: Replace the variables within {}
    // findingPath: The relative resource name of the finding. See:
    // https://cloud.google.com/apis/design/resource_names#relative_resource_name
    // Use any one of the following formats:
    //  - organizations/{org_id}/sources/{source_id}/locations/{location}/finding/{finding_id}
    //  - folders/{folder_id}/sources/{source_id}/locations/{location}/finding/{finding_id}
    //  - projects/{project_id}/sources/{source_id}/locations/{location}/finding/{finding_id}
    //
    String findingPath = "{path-to-the-finding}";

    setMute(findingPath);
  }

  // Mute an individual finding.
  // If a finding is already muted, muting it again has no effect.
  // Various mute states are: MUTE_UNSPECIFIED/MUTE/UNMUTE.
  public static Finding setMute(String findingPath) throws IOException {
    // Initialize client that will be used to send requests. This client only needs to be created
    // once, and can be reused for multiple requests.
    try (SecurityCenterClient client = SecurityCenterClient.create()) {

      SetMuteRequest setMuteRequest =
          SetMuteRequest.newBuilder()
              // Relative path for the finding.
              .setName(findingPath)
              .setMute(Mute.MUTED)
              .build();

      Finding finding = client.setMute(setMuteRequest);
      System.out.println(
          "Mute value for the finding " + finding.getName() + " is: " + finding.getMute());
      return finding;
    }
  }
}
// [END securitycenter_set_mute_v2]
