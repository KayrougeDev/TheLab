#version 330
#include veil:space_helper

in vec2 texCoord;
out vec4 fragColor;

uniform sampler2D DiffuseSampler0;
uniform sampler2D DiffuseDepthSampler;

uniform float time;
uniform vec3 playerPos;
uniform float speed;
uniform float width;

void main() {
    float depth = texture(DiffuseDepthSampler, texCoord).r;
    vec4 worldPos4 = screenToWorldSpace(texCoord, depth);
    vec3 worldPos = worldPos4.xyz;
    float dist = distance(worldPos, playerPos);
    float radius = time * speed;
    float t = smoothstep(width/2.0, 0.0, abs(dist - radius));
    vec4 base = texture(DiffuseSampler0, texCoord);
    vec3 wave = mix(base.rgb, vec3(1.0), t);

    if(worldPos.y > 350 || worldPos.y < -65) {
        fragColor = base;
        return;
    }

    fragColor = vec4(wave, base.a);
}
