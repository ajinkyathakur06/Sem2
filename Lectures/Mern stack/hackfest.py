from PIL import Image
import piexif

# Function to extract and decode EXIF data
def extract_and_decode_exif(image_path):
    try:
        # Load EXIF data
        exif_dict = piexif.load(image_path)
        
        # Print the EXIF data for debugging
        if exif_dict:
            print("EXIF data found:")
            for ifd, tags in exif_dict.items():
                if tags:  # Check if the section is not None
                    print(f"\n--- {ifd} ---")
                    for tag, value in tags.items():
                        try:
                            tag_name = piexif.TAGS[ifd][tag]["name"]
                            print(f"{tag_name}: {value}")
                        except KeyError:
                            print(f"Unknown Tag {tag}: {value}")
                else:
                    print(f"\n--- {ifd} ---")
                    print("No data in this section.")
        else:
            print("No EXIF data found in the image.")
    except Exception as e:
        print(f"Error: {e}")

# Path to your image
image_path = r"C:\Users\Lenovo\Downloads\Hacker.jpg"

# Call the function
extract_and_decode_exif(image_path)
